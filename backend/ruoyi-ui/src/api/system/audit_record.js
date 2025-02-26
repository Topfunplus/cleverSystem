import request from '@/utils/request'

// 查询用于存储访客审核相关记录的列表
export function listAuditRecord(query) {
  return request({
    url: '/system/audit_record/list',
    method: 'get',
    params: query
  })
}

// 查询用于存储访客审核相关记录的详细
export function getAuditRecord(id) {
  return request({
    url: '/system/audit_record/' + id,
    method: 'get'
  })
}

// 新增用于存储访客审核相关记录的
export function addAuditRecord(data) {
  return request({
    url: '/system/audit_record',
    method: 'post',
    data: data
  })
}

// 修改用于存储访客审核相关记录的
export function updateAuditRecord(data) {
  return request({
    url: '/system/audit_record',
    method: 'put',
    data: data
  })
}

// 删除用于存储访客审核相关记录的
export function delAuditRecord(id) {
  return request({
    url: '/system/audit_record/' + id,
    method: 'delete'
  })
}
