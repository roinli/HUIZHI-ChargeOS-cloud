# ws-wx-privacy  隐私保护弹窗组件

本组件基于微信小程序官方政策开发，[关于小程序隐私保护指引设置的公告](https://developers.weixin.qq.com/community/develop/doc/00042e3ef54940ce8520e38db61801?blockType=1&page=1#comment-list)

`wsWxPrivacy` 组件用于显示隐私保护提示的弹窗，用户可以选择同意或不同意隐私协议。

# 请注意 微信后台的用户隐私保护指引必须审核通过之后才可以调试

### 注意事项  
1. 请使用HbuilderX导入，因为本组件依赖`uni-popup`直接下载会丢失依赖。  
2. 部分隐私接口需要用`wx.requirePrivacyAuthorize`接口包装，请仔细阅读[微信小程序隐私协议开发指南](https://developers.weixin.qq.com/miniprogram/dev/framework/user-privacy/PrivacyAuthorize.html)
3. popup可以自行替换为自己使用的组件，以减小小程序包体积，不过需要注意使用`util.js`中的方法获取组件并打开，使用ref可能会因为this指向的问题导致无法打开弹出框
4. 导入demo项目请选择vue3版本，导入组件不限。

### 隐私指引调试配置（必须配置完成才可以顺利调试）
执行完以下三个步骤之后才可以顺利调试隐私指引内容，如有问题请先自查下边三个步骤是否都完成

#### 1.更新用户隐私保护指引(必需) 
小程序管理员或开发者可以根据具体小程序涉及到的[隐私相关接口](https://developers.weixin.qq.com/miniprogram/dev/framework/user-privacy/miniprogram-intro.html)来更新微信小程序后台的`用户隐私保护指引`，更新并审核通过后就可以进行相关的开发调试工作。

`需要注意的是，仅有在指引中声明所处理的用户信息，才可以调用平台提供的对应接口或组件。若未声明，对应接口或组件将直接禁用`

`微信后台的用户隐私保护指引必须审核通过之后才可以调试，必须审核通过!审核通过!审核通过!`

#### 2.配置调试字段__usePrivacyCheck__ （必需）
参考 [微信小程序隐私协议开发指南](https://developers.weixin.qq.com/miniprogram/dev/framework/user-privacy/PrivacyAuthorize.html)中的介绍

目前 `getPrivacySetting`、`onNeedPrivacyAuthorization`、`requirePrivacyAuthorize` 等接口目前可以正常接入调试。调试说明：

- 在 2023 年 9 月 15 号之前，在 app.json 中配置 `"__usePrivacyCheck__": true` 后，会启用隐私相关功能，如果不配置或者配置为 false 则不会启用。

- 在 2023 年 9 月 15 号之后，不论 app.json 中是否有配置 **usePrivacyCheck**，隐私相关功能都会启用。

所以在基于`uni-app`开发时，我们在 2023 年 9 月 15 号之前进行相关开发调试则需要在`manifest.json`文件`mp-weixin`中添加`"__usePrivacyCheck__": true`

#### 3.配置微信开发工具基础库（必需）
将调试基础库改为3.0.0以上。具体路径为:  
微信开发者工具->详情->本地设置->调试基础库

### Props

| 属性				| 类型		| 默认值							| 描述																																									|
|------------		|--------	|----------------------------------	|------------------------------------------------------------------------------------------																				|
| title				| String	| 用户隐私保护提示					| 弹窗标题。																																							|
| desc				| String	| 感谢您使用本应用，...				| 弹窗描述。																																							|
| protocol			| String	| 用户隐私保护指引					| 隐私保护指引的名称。																																					|
| subDesc			| String	| 。当您点击同意并开始时...			| 子描述，进一步解释同意隐私协议的含义。																																|
| disagreeEnabled	| Boolean	| true								| 是否允许拒绝。控制是否可以点击不同意按钮并执行后续逻辑。如果设置为 true，用户可以点击不同意按钮执行后续逻辑。如果设置为 false，点击不同意按钮会显示提示信息，但不会执行后续逻辑。	|
| disagreePromptText| String	| 请先仔细阅读并同意隐私协议		| 不同意按钮的提示消息内容。																																		|

### Events

| 事件      | 参数                                   | 描述                                                                                   |
|-----------|----------------------------------------|----------------------------------------------------------------------------------------|
| disagree  | 无参数                                 | 用户选择不同意隐私协议时触发的事件。                                                       |
| agree     | 无参数                                 | 用户选择同意隐私协议时触发的事件。                                                       |


### 使用示例
请确保你已经阅读或了解过[uni-modules 规范](https://uniapp.dcloud.net.cn/plugin/uni_modules.html#uni-modules)  
使用hbuildX导入组件后可以直接使用，无需import。

```vue
<template>
  <view>
  <!-- #ifdef MP-WEIXIN -->
  <ws-wx-privacy id="privacy-popup"></ws-wx-privacy>
  <!-- #endif -->
  </view>
</template>
<script>

export default {
  components: {},
  methods: {
    handleDisagree() {
      // 处理用户不同意隐私协议的逻辑
    },
    handleAgree() {
      // 处理用户同意隐私协议的逻辑
    }
  }
}
</script>
```

请注意，上述示例中的组件路径和事件处理逻辑可能需要根据实际情况进行调整。其中`id="privacy-popup"`必填，目的是为了解决多个连续页面同时引入，造成的this指向错误的问题。

## 授权协议
```
1. 企业或个人可以免费商用。
2. 企业或个人不得复制、改造传播并分发本组件。
3. 如果你想分享本组件及教程，请保留本组件的下载地址以及作者名称。
```

## Vue3高颜值组件库推荐
[wot-design-uni](https://ext.dcloud.net.cn/plugin?id=13889)