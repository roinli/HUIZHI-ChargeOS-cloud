import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);
import Layout from "@/layout";
/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: "/login",
    component: () => import("@/views/login/indexNew"),
    hidden: true,
  },

  {
    path: "/",
    component: Layout,
    redirect: "/home",
    meta: {
      title: "主页",
      icon: "dashboard",
    },
    children: [
      {
        path: "home",
        name: "home",
        component: () => import("@/views/home"),
        meta: {
          title: "地图概览",
        },
      },
    ],
  },
];

export const asyncRouterMap = [
  {
    path: "/statistics",
    component: Layout,
    name: "statistics",
    redirect: "/statistics",
    meta: {
      title: "统计",
      icon: "tongji",
      noCache: true,
    },
    children: [
      {
        path: "/statisticsList",
        name: "statisticsList",
        component: () => import("@/views/statistics/userStatistics"),
        meta: {
          title: "数据统计",
        },
      },
    ],
  },
  {
    path: "/device",
    component: Layout,
    name: "device",
    redirect: "noRedirect",
    meta: {
      title: "设备管理",
      icon: "shebei",
    },
    children: [
      {
        path: "deviceList",
        name: "deviceList",
        component: () => import("@/views/device/deviceList"),
        meta: {
          title: "充电桩管理",
        },
      },
      {
        path: "plotList",
        name: "plotList",
        component: () => import("@/views/device/plotList"),
        meta: {
          title: "站点管理",
        },
      },
    ],
  },
  {
    path: "/order",
    component: Layout,
    children: [
      {
        path: "/orderList",
        name: "orderList",
        component: () => import("@/views/order/orderList"),
        meta: {
          title: "订单管理",
          icon: "dingdan",
        },
      },
    ],
  },
  {
    path: "/rulesmanage",
    component: Layout,
    redirect: "noRedirect",
    name: "rulesmanage",
    meta: {
      title: "规则管理",
      icon: "guize",
    },
    children: [
      {
        path: "/rules/list",
        name: "rulesList",
        component: () => import("@/views/rules/list"),
        meta: {
          title: "规则列表",
          icon: "nested",
        },
      },
    ],
  },
  {
    path: "/systemUser",
    component: Layout,
    name: "systemUser",
    redirect: "noRedirect",
    meta: {
      title: "系统管理",
      icon: "nested",
    },
    children: [
      {
        path: "user",
        name: "user",
        component: () => import("@/views/systemUser/userList"),
        meta: {
          title: "用户管理",
        },
      },
    ],
  },

  {
    path: "*",
    redirect: "/",
    hidden: true,
  },
];
const createRouter = () =>
  new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({
      y: 0,
    }),
    routes: constantRoutes,
  });

const router = createRouter();

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter();
  router.matcher = newRouter.matcher; // reset router
}
export default router;
