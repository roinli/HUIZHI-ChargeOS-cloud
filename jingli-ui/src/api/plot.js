import request from '@/utils/request'

const baseService = '/jingli-admin';
// plot/getPlotList
export function getPlotList(data) {
    return request({
        url: baseService + '/plot/getPlotList',
        method: 'get',
        params: data

    })
}