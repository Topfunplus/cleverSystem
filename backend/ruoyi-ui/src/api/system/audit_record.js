import request from '@/utils/request'

// 查询用于存储访客审核相关记录的列表
export function listAudit_record(query) {
  return request({
    url: '/system/audit_record/list',
    method: 'get',
    params: query
  })
}

// 查询用于存储访客审核相关记录的详细
export function getAudit_record(id) {
  return request({
    url: '/system/audit_record/' + id,
    method: 'get'
  })
}

// 新增用于存储访客审核相关记录的
export function addAudit_record(data) {
  return request({
    url: '/system/audit_record',
    method: 'post',
    data: data
  })
}

// 修改用于存储访客审核相关记录的
export function updateAudit_record(data) {
  return request({
    url: '/system/audit_record',
    method: 'put',
    data: data
  })
}

// 删除用于存储访客审核相关记录的
export function delAudit_record(id) {
  return request({
    url: '/system/audit_record/' + id,
    method: 'delete'
  })
}
