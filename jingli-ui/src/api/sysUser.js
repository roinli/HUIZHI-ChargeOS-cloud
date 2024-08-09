import request from '@/utils/request'

const baseService = '/jingli-admin';

// POST /sysUser/getUserList
export function getUserList(data) {
    return request({
        url: baseService + '/sysUser/getUserList',
        method: 'post',
        params: data

    })
}