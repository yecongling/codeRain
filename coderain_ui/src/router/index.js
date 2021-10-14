import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

import layout from '@/layout'

export const constantRoutes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: (resolve) => require(['@/views/login'], resolve)
  },
  {
    path: '/about',
    name: 'About',
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

export default new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: constantRoutes
})
