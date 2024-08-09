import request from '@/utils/request'

const baseService = '/jingli-admin';
// 登录
export function login(data) {
  return request({
    url: baseService + '/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: {
      token
    }
  })
}

//GET /geDataScopeList
export function getDataScopeList(data) {
  return request({
    url: baseService + '/getDataScopeList',
    method: 'get',
    params: {
      data
    }
  })
}

export function getUserMenu(token) {
  return request({
    url: baseService + '/menu/getUserMenu',
    method: 'get',
    params: {
      token
    }
  })
}