import request from '@/utils/request'

const baseService = '/jingli-admin';

//GET /sysRule/getRuleList
export function getRuleList(data) {
    return request({
        url: baseService + '/sysRule/getRuleList',
        method: 'get',
        params: data

    })
}