import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('@/components/ApplyInfo.vue')
    },
    // 登录和注册页面
    {
      path: '/login',
      name: 'login',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('@/views/Login.vue')
    },
    // 审核人审核页面
    {
      path: '/audit',
      name: 'audit',
      component: () => import('@/views/AuditList.vue')
    }
  ]
})

export default router
