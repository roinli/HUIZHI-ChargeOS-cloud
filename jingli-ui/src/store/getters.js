const getters = {
  size: state => state.app.size,
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  addRouters: state => state.permission.addRouters,
  routers: state => state.permission.routers,
  routes: state => state.permission.routers,
  permission_routes: state => state.permission.routers,
  busiid: state => state.info.busiid,
  businame: state => state.info.businame,
  controlinfo: state => state.info.controlinfo,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
}
export default getters
