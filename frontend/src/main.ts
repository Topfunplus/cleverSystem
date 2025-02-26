import './assets/main.css'
import 'vant/lib/index.css'
import { createApp } from 'vue'
import { createPinia, storeToRefs } from 'pinia'
import App from './App.vue'
import router from './router'
import 'sweetalert2/src/sweetalert2.scss'

// 注册自定义指令
import { useUserStore } from '@/stores/user'

const app = createApp(App)

// 注册自定义指令
app.directive('hasPermission', (el: HTMLElement, binding: any, vnode: any) => {
  console.log('自定义权限开始安装')
  const { value } = binding
  const all_permission = '*:*:*'

  const user = useUserStore()
  const { getPermissions } = storeToRefs(user)
  const permissions: string[] = getPermissions.value
  console.log('自定义权限 权限为' + permissions)

  if (value && value instanceof Array && value.length > 0) {
    const permissionFlag = value

    const hasPermissions = permissions.some((permission) => {
      return all_permission === permission || permissionFlag.includes(permission)
    })

    if (!hasPermissions) {
      el.parentNode && el.parentNode.removeChild(el)
    }
  } else {
    throw new Error(`请设置操作权限标签值`)
  }
})

app.use(createPinia())
app.use(router)

app.mount('#app')
