<template>
  <div>
    <!--主容器-->
    <el-container>
      <!--头部-->
      <el-header style="text-align: right">
        <span>
          {{username}}
        </span>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <span>
          <el-button size="small" type="waring" @click="onLogout">退出</el-button>
        </span>
      </el-header>
      <!--小容器-->
      <el-container>
        <!--侧边-->
        <el-aside width="200px">
          <!--侧边内的菜单-->
          <el-menu
              class="el-menu-vertical-demo">
            <!--子菜单-->
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>学生管理</span>
              </template>
              <!--菜单选项-->
              <el-menu-item index="1">
                <router-link to="/admin/stu" tag="span">学生信息管理</router-link>
              </el-menu-item>
              <el-menu-item index="2">宿舍信息管理</el-menu-item>
              <el-menu-item index="3">选课信息管理</el-menu-item>
              <el-menu-item index="4">成绩信息管理</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <!--小容器-->
        <el-container>
          <!--页面的主要显示内容-->
          <el-main>
            <!--此处是页面展示的主区域，要能切换页面，需要展示路由页面出口-->
            <router-view> </router-view>
          </el-main>
          <!--页面底部-->
          <el-footer>Zhaojiawei © 2024 HPU Copyright</el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "AdminView",
  data(){
    return{
      //取出数据，赋值给变量
      username:window.localStorage.getItem("username")
    }
  },
  methods:{
    onLogout(){
      this.$router.push("/")
    }
  }
}
</script>

<!--页面的样式-->
<style scoped>
.el-header, .el-footer {
  background-color: #3c8dbc;
  color: #fffff2;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #2f4050;
  color: #a7b1c2;
  text-align: center;
  line-height: 200px;
  /*侧边栏高度*/
  height: 650px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;

}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>