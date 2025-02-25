import request from '@/utils/request'

// 查询用于存储访客相关信息的列表
export function listVisitorInfo(query) {
  return request({
    url: '/system/visitor_info/list',
    method: 'get',
    params: query
  })
}

// 查询用于存储访客相关信息的详细
export function getVisitorInfo(id) {
  return request({
    url: '/system/visitor_info/' + id,
    method: 'get'
  })
}

// 新增用于存储访客相关信息的
export function addVisitorInfo(data) {
  return request({
    url: '/system/visitor_info',
    method: 'post',
    data: data
  })
}

// 修改用于存储访客相关信息的
export function updateVisitorInfo(data) {
  return request({
    url: '/system/visitor_info',
    method: 'put',
    data: data
  })
}

// 删除用于存储访客相关信息的
export function delVisitorInfo(id) {
  return request({
    url: '/system/visitor_info/' + id,
    method: 'delete'
  })
}
