const http = uni.$u.http

// 获取账户信息
export const getAccountInfo=(params) => http.get('/me/accountInfo',params)
// 获取账户余额
export const queryAccountBalance=(params) => http.get('/me/queryAccountBalance',params)
// 绑定手机号
export const appletBindMobile=(params) => http.get('/me/appletBindMobile',params)

// 获取字典
export const getCommonCode=(params) => http.get('/me/getCommonCode',params)
// 保存反馈
export const saveFeedBack=(params) => http.get('/me/saveFeedBack',params)
// 获取广告
export const showAdvert=(params) => http.get('/me/showAdvert',params)
// 获取充电站
export const getPlotInfo=(params) => http.get('/charging/getPlotInfo',params)
// 获取充电站列表
export const getPlotInfoPage=(params) => http.get('/charging/getPlotInfoPage',params)
// 获取充电桩信息
export const getChargingPileData=(params) => http.get('/charging/getChargingPileData',params)

// 微信登录
export const appletWeChatLogin=(params) => http.get('/login/appletWeChatLogin',params)


// 保存订单
export const saveOrder=(params) => http.get('/order/saveOrder',params)



// 根据key查询配置信息
export const queryConfigByKey=(params) => http.get('/charging/queryConfigByKey',params)
// 根据类型查看文章
export const queryNewsByNewsConfigKey=(params) => http.get('/charging/queryNewsByNewsConfigKey',params)
// 根据类型查看列表
export const queryNewsNameList=(params) => http.get('/charging/queryNewsNameList',params)
