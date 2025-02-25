import request from '@/utils/request'

// 查询用于存储访客审核相关记录的列表
export function listQrCodeRecord(query) {
  return request({
    url: '/system/qr_code_record/list',
    method: 'get',
    params: query
  })
}

// 查询用于存储访客审核相关记录的详细
export function getQrCodeRecord(id) {
  return request({
    url: '/system/qr_code_record/' + id,
    method: 'get'
  })
}

// 新增用于存储访客审核相关记录的
export function addQrCodeRecord(data) {
  return request({
    url: '/system/qr_code_record',
    method: 'post',
    data: data
  })
}

// 修改用于存储访客审核相关记录的
export function updateQrCodeRecord(data) {
  return request({
    url: '/system/qr_code_record',
    method: 'put',
    data: data
  })
}

// 删除用于存储访客审核相关记录的
export function delQrCodeRecord(id) {
  return request({
    url: '/system/qr_code_record/' + id,
    method: 'delete'
  })
}
