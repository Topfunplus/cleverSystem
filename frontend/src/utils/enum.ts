// export enum AuditStatus {
//   UNAUDITED = {
//     value: 0,
//     text: '未审核'
//   },
//   AUDIT_PASS = {
//     value: 1,
//     text: '审核通过'
//   },
//   AUDIT_FAIL = {
//     value: 2,
//     text: '审核不通过'
//   }
// }


export const AuditStatus =  Object.freeze(
  {
    UNAUDITED : {
      value: 0,
      text: '审核中'
    },
    AUDIT_PASS : {
      value: 1,
      text: '审核通过'
    },
    AUDIT_FAIL: {
      value: 2,
      text: '审核不通过'
    }
  }
)