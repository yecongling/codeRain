import Vue from 'vue'

import Cookies from 'js-cookie'

import Element from 'element-ui'
import './assets/styles/element-variables.scss'
import App from './App'
import store from './store'
import router from './router'

import './assets/icons'
import '@/assets/styles/index.scss' // global css
import '@/assets/styles/coderain.scss' // coderain css
// 头部标签组件
import VueMeta from 'vue-meta'

Vue.use(VueMeta)

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */

Vue.use(Element, {
  size: Cookies.get('size') || 'medium' // set element-ui default size
})

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
