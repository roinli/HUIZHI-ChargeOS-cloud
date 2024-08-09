// store/permission.js
import {asyncRouterMap, constantRoutes} from '@/router';

function hasPermission(roles, route) {
    if (route.meta && route.meta.role) {
        return roles.some(role => route.meta.role.indexOf(role) >= 0)
    } else {
        return true
    }
}

function filterMenus(menus, keys) {
    for (var i = 0; i < menus.length; i++) {
        const item = menus[i]
        if (item.children && item.children.length > 0) {
            const childrenItem = filterMenus(item.children, keys)
            if (childrenItem.length > 0) {
                menus[i].children = childrenItem
            } else {
                delete menus[i].children
                if (keys.indexOf(item.path) === -1) {
                    menus.splice(i, 1);
                    i--;
                }
            }
        } else {
            if (keys.indexOf(item.path) === -1) {
                menus.splice(i, 1);
                i--;
            }
        }
    }

    return menus
}

function getPathKeys(menus, keys = []) {
    menus.forEach(v => {
        if (v.frontendUrl) {
            keys.push(v.frontendUrl)
            if (v.children && v.children.length > 0) {
                keys.concat(getPathKeys(v.children, keys))
            }
        }
    })
    return keys
}

const permission = {
    state: {
        routers: constantRoutes,
        addRouters: [],
    },
    mutations: {
        SET_ROUTERS: (state, routers) => {
            state.addRouters = routers;
            state.routers = constantRoutes.concat(routers);
        }
    },
    actions: {
        GenerateRoutes({commit}, menus) {
            return new Promise(resolve => {
                const keys = getPathKeys(menus.res).concat(['*'])
                const accessedRouters = filterMenus(asyncRouterMap, keys);
                //  const accessedRouters = asyncRouterMap
                commit('SET_ROUTERS', accessedRouters);
                resolve();
            })
        },
        ResetRoutes({commit}, menus) {
            return new Promise(resolve => {
                commit('SET_ROUTERS', menus.res);
                resolve();
            })
        }
    }
};

export default permission;

