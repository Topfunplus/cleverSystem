import request from '@/utils/request'

// 查询用于存储访客二维码相关记录的列表
export function listQr_code_record(query) {
  return request({
    url: '/system/qr_code_record/list',
    method: 'get',
    params: query
  })
}

// 查询用于存储访客二维码相关记录的详细
export function getQr_code_record(id) {
  return request({
    url: '/system/qr_code_record/' + id,
    method: 'get'
  })
}

// 新增用于存储访客二维码相关记录的
export function addQr_code_record(data) {
  return request({
    url: '/system/qr_code_record',
    method: 'post',
    data: data
  })
}

// 修改用于存储访客二维码相关记录的
export function updateQr_code_record(data) {
  return request({
    url: '/system/qr_code_record',
    method: 'put',
    data: data
  })
}

// 删除用于存储访客二维码相关记录的
export function delQr_code_record(id) {
  return request({
    url: '/system/qr_code_record/' + id,
    method: 'delete'
  })
}
