import instance from '@/utils/http'

// 批量上传接口
export function uploadBatch(files: File[]): Promise<{
  msg: string,
  urls: string,
  code: number,
} & unknown | undefined> {
  const formData = new FormData()
  files.forEach((file) => {
    formData.append('files', file)
  })

  return instance.request({
    url: '/common/uploads', // 接口地址
    method: 'post', // 请求方法
    data: formData, // 请求体
    headers: {
      'Content-Type': 'multipart/form-data' // 设置请求头为 multipart/form-data
    }
  })
}
