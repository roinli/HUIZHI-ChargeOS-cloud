import request from '@/utils/request'

const baseService = '/jingli-admin';

export function getInfoByKey(data) {
    return request({
        url: baseService + '/sysConfig/getInfoByKey',
        method: 'get',
        params: data

    })
}
