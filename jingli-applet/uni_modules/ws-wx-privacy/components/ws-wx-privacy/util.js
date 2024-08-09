/**
 * 获取当前页面上下文
 * @returns 页面对象
 */
export function getContext() {
	// eslint-disable-next-line no-undef
	const pages = getCurrentPages()
	return pages[pages.length - 1]
}

/**
 * 获取上下文中指定节点组件
 * @param context 选择器的选择范围，可以传入自定义组件的 this 作为上下文
 * @param selector 自定义节点选择器
 */
export function getComponent(context, selector ) {
	let component = null
	// #ifdef H5
	context.$children.forEach((child) => {
		if (`#${child.$attrs.id}` === selector) {
			component = child
		} else if (child.$children && child.$children.length) {
			if (getComponent(child, selector)) {
				component = getComponent(child, selector)
			}
		}
		if (component) {
			return component
		}
	})
	// #endif
	// #ifdef MP-WEIXIN
	component = context.selectComponent && context.selectComponent(selector) && context.selectComponent(selector).$vm
	// #endif

	// #ifdef MP-ALIPAY
	const alipay = context.$children ? context.$children : context.$vm && context.$vm.$children ? context.$vm
		.$children : []
	component = alipay.find((component) => {
		return `#${component.$scope.props.id}` === selector
	})
	// #endif
	// #ifdef APP-PLUS
	const app = context.$children ? context.$children : context.$vm && context.$vm.$children ? context.$vm.$children :
	[]
	component = app.find((component) => {
		return `#${component.$attrs.id}` === selector
	})
	// #endif
	return component
}