import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginView from "@/views/LoginView";
import AdminView from "@/views/AdminView";
import StuInfoView from "@/views/StuInfoView";


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: LoginView
  },
  {
    path: '/admin',
    name: 'admin',
    component: AdminView,
    children:[
      {
        path: 'stu',
        name: 'stu',
        component: StuInfoView,
      }
    ]
  },

]

const router = new VueRouter({
  routes
})

export default router