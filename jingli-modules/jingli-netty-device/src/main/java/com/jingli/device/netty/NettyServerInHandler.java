package com.jingli.device.netty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.jingli.common.core.utils.StringUtils;
import io.netty.channel.ChannelHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;


@Slf4j
@ChannelHandler.Sharable
@Component
@AllArgsConstructor
public class NettyServerInHandler extends SimpleChannelInboundHandler<String> {
	static DateFormat taskEndDf = new SimpleDateFormat("*MM*dd*HH:mm:ss");
	private static Map<String, ChannelHandlerContext> CHANNEL_CTX_MAP = new HashMap<>();
	private static Map<ChannelHandlerContext, String> CTX_MAP = new HashMap<>();
	private static Set<String> taskSet = new HashSet<>();
	

	/**
	 * 1.心跳包：	PTDN,公司ID,组织ID,包类型,设备类型,设备ID,END
	 * 2.数据包：	PTDN,公司ID,组织ID,包类型,设备类型,设备ID,数据,END
	 * 
	 * 说明：	包类型	0农业、1渔业、2大田、3仓库
	 * 		设备类型	0动态设备(控制设备)、1静态设备(传感器类)
	 */
	// 读取客户端发送的数据
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String sourceMsg) throws Exception {
		if (sourceMsg != null && !"".equals(sourceMsg)) {
			log.info("收到源消息: {}" , sourceMsg);
			String[] msgs = sourceMsg.split("PTDN");
			for (int i = 0; i < msgs.length; i++) {
				String msg = msgs[i];
				if(msg != null && !msg.equals("")){
					msg = "PTDN" + msg;
					log.debug("拆包消息:" + msg);
					String request[] = msg.split(",");
					if (request != null && request.length >= 7) {
						String endStr = request[request.length - 1];
						if(endStr!=null && !"".equals(endStr)&& endStr.length() > 3){
							endStr = endStr.substring(0, 3);
						}
						if ("PTDN".equals(request[0]) && "END".equals(endStr)) {
						//if ("PTDN".equals(request[0]) && "END".equals(request[request.length - 1])) {
							int companyId = Integer.parseInt(request[1]);
							int gfNum = Integer.parseInt(request[2]);
							int packageType = Integer.parseInt(request[3]);
							int deviceType = Integer.parseInt(request[4]);
							String deviceIdStr = request[5];
//							int deviceId = 0;
//							int deivceGroup = 1;
//							if(!StringUtil.isEmpty(deviceIdStr) && deviceIdStr.indexOf("-") != -1 ){
//								deviceId = Integer.parseInt(deviceIdStr.split("-")[0]);
//								deivceGroup = Integer.parseInt(deviceIdStr.split("-")[1]);
//							}
							String flag = companyId + "&" + gfNum + "&" + packageType + "&" + deviceType + "&" + deviceIdStr;
							String nettyKey = companyId + "&" + gfNum  + "&" + packageType + "&";
							if (request.length == 7) {
								// 设备心跳数据
								CHANNEL_CTX_MAP.put(nettyKey, ctx);
								CTX_MAP.put(ctx, nettyKey);
								String pushToAppMsg = "DH&" + flag;
								sendToApp(pushToAppMsg);
							} else if (request.length == 8) {
								// 设备发送数据
								CHANNEL_CTX_MAP.put(nettyKey, ctx);
								CTX_MAP.put(ctx, nettyKey);
								String dataVal = request[6];
								String pushToAppMsg = "";
								if("E-1".equals(dataVal)){
									pushToAppMsg = "DE&" + flag + "&" + 1;
								}else if("E-0".equals(dataVal)){
									pushToAppMsg = "DE&" + flag + "&" + 0;
								}else{
									pushToAppMsg = "DD&" + flag + "&" + dataVal;
								}
								sendToApp(pushToAppMsg);
							} else if (request.length == 9) {
								//定时任务回执信息（例：PTDN,126,1,0,0,63-1,1,3V0BPSLN,END*08*31*17:36:10）
								CHANNEL_CTX_MAP.put(nettyKey, ctx);
								CTX_MAP.put(ctx, nettyKey);
								String taskId = request[7];
								taskSet.add(taskId);
								String pushToAppMsg = "DD&" + flag + "&" + request[6];
								sendToApp(pushToAppMsg);
							}
						}else{
							
							
							// TEST,公司ID，ID，包类型，数据，END
							// 编写测试接口
							if ("PTDNTEST".equals(request[0]) && "END".equals(endStr)) {
								ChannelHandlerContext channelHandlerContext = null;
								int companyId = Integer.parseInt(request[1]);
								int gfNum = Integer.parseInt(request[2]);
								int packageType = Integer.parseInt(request[3]);
								String data = request[4]+"";
								String nettyKey = companyId + "&" + gfNum  + "&" + packageType + "&";
								/*CHANNEL_CTX_MAP.put(nettyKey, ctx);
								CTX_MAP.put(ctx, nettyKey);*/
								String toUser = companyId + "&" + gfNum  + "&" +packageType + "&";
								channelHandlerContext = CHANNEL_CTX_MAP.get(toUser);
								if (channelHandlerContext != null && channelHandlerContext.channel().isActive()){
									log.debug("服务端下发主动消息成功：" + msg);
									channelHandlerContext.writeAndFlush(data);
								}
							}
							
							//log.debug("数据格式异常:" + msg);
						}
					} 
				}
			}
		}
	}

	// 新客户端接入
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		System.out.println("===新客户端接入===");
		log.debug("新客户端接入...");
	}

	// 客户端断开
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.debug("设备断开连接");
		deviceOffLine(ctx);

	}

	// 异常
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		// 关闭通道
		ctx.channel().close();
		// 打印异常
		deviceOffLine(ctx);
	}
	
	public void deviceOffLine(ChannelHandlerContext ctx){
		String s = CTX_MAP.get(ctx);
		if(StringUtils.isEmpty(s)){
			return;
		}
		String pushToAppMsg = "OFF&" + s;
		sendToApp(pushToAppMsg);
		if(CHANNEL_CTX_MAP != null){
			CHANNEL_CTX_MAP.remove(s);
		}
		if(CTX_MAP != null){
			CTX_MAP.remove(ctx);
		}
		log.debug("客户端断开...");
	}
	
	public void sendToApp(String msg){
//		com.jingli.netty.RedisUtils.addList( "WEB_NOTICE" , msg);
	}
	
	/**
	 * 推送至设备端方法
	 * @param msg 控制消息
	 */
	public static void sendToDevice(String msg){
		String request[] = msg.split(",");
		ChannelHandlerContext channelHandlerContext = null;
		if(request!= null){
			if("WCD".equals(request[0])){
				//WEB主动下发数据
				log.debug("开始执行主动消息：" + msg);
				String companyId = request[2];
				String gfNum = request[3];
				String packageType = request[4];
				String toUser = companyId + "&" + gfNum  + "&" +packageType + "&";
				channelHandlerContext = CHANNEL_CTX_MAP.get(toUser);
				if (channelHandlerContext != null && channelHandlerContext.channel().isActive()){
					log.debug("服务端下发主动消息成功：" + msg);
					channelHandlerContext.writeAndFlush(msg.replace("WCD,", ""));
				}
				else{
					log.debug("socket断开,服务端下发主动消息失败：" + msg);
				}
			}else if("DF".equals(request[0])){
				//通知设备断线
				log.debug("通知设备断线：" + msg);
				String companyId = request[2];
				String gfNum = request[3];
				String packageType = request[4];
				String toUser = companyId + "&" + gfNum  + "&" +packageType + "&";
				channelHandlerContext = CHANNEL_CTX_MAP.get(toUser);
				if (channelHandlerContext != null && channelHandlerContext.channel().isActive()){
					log.debug("服务端下发断线消息成功：" + msg);
					channelHandlerContext.writeAndFlush(msg.replace("DF,", ""));
				}
				else{
					log.debug("socket断开,服务端下发断线消息失败：" + msg);
				}
			}else if("TCD".equals(request[0])){
				//定时任务(格式：TCD,PTDN,112,1,1,0,3,0,a1b2c3,0,END,次数)
				String currentDateTime = taskEndDf.format(new Date());
				String companyId = request[2];
				String gfNum = request[3];
				String packageType = request[4];
				String toUser = companyId + "&" + gfNum  + "&" +packageType + "&";
				String deviceType = request[5];
				String deviceId = request[6];
				String state = request[7];
				String taskId = request[8];
				String repetCnt = request[9];
				String delayCnt = "0";
				try {
					delayCnt = request[11];
				} catch (Exception e) {
					log.debug("旧数据格式异常");
				}
				int repetCount = Integer.parseInt(repetCnt);
				int delayCount = Integer.parseInt(delayCnt);
				if(delayCount != 0){
//					log.debug("定时任务(防止粘包跳过等待下次执行)：" + msg);
				}else{
					log.debug("定时任务：" + msg);
				}
				if(taskSet.contains(taskId)){
					//该任务已执行完毕
					log.debug("定时任务执行完毕：" + taskId);
					taskSet.remove(taskId);
					return;
				}
				if(delayCount != 0){
					delayCount--;
					String tcdMsg = "TCD,PTDN,"+companyId + "," + gfNum  + "," 
							+ packageType  + "," + deviceType  + "," 
							+ deviceId + "," + state + "," + taskId + "," 
							+ repetCount + ",END," + delayCount;
//					com.jingli.netty.RedisUtils.addList("NETTY_NOTICE", tcdMsg);
					return;
				}
				channelHandlerContext = CHANNEL_CTX_MAP.get(toUser);
				
				String conMsg = "PTDN,"+companyId + "," + gfNum  + "," 
						+ packageType  + "," + deviceType  + "," 
						+ deviceId + "," + state + "," + taskId
						+ ",END" + currentDateTime;
				if (channelHandlerContext != null && channelHandlerContext.channel().isActive()){
					log.debug("服务端下发定时控制成功：" + conMsg);
					channelHandlerContext.writeAndFlush(conMsg);
				}else{
					log.debug("socket断开,服务端下发定时控制失败：" + conMsg);
				}
				repetCount++;
				if(repetCount < 100){
					//更改推送次数+1
					String tcdMsg = "TCD,PTDN,"+companyId + "," + gfNum  + "," 
							+ packageType  + "," + deviceType  + "," 
							+ deviceId + "," + state + "," + taskId + "," 
							+ repetCount + ",END,1";
//					com.jingli.netty.RedisUtils.addList("NETTY_NOTICE", tcdMsg);
				}
				log.debug("任务下发次数：" + repetCount);
			}
		}
	}
}
