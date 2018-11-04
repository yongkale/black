<template>
<div class="action-detail">
    <div class="action-detail-header">
        <mt-header title="活动详情">
            <router-link to="/" slot="left">
                <mt-button icon="back">返回</mt-button>
            </router-link>
        </mt-header>
    </div>
    <div>
        
        <img class="action-img" :src="qiniu.qiniu + '/' + dateAction.dateActionImg">
        <div class="action-theme">
            <ul>
                <li>活动主题: {{dateAction.theme}}</li>
                <li>人均消费: {{dateAction.consumption}}</li>
                <li>开始时间: {{dateAction.time | formatDate}}</li>
                <li>活动介绍: {{dateAction.introduction}}</li>
                <li>活动举办方: {{dateAction.theme}}</li>
            </ul>
        </div>
    </div>
    <div id="peopleCount" class="people-count"></div>
    <div id="peopleRatio" class="people-ratio"></div>
    <div class="submit-action-div" @click="joinAction"> 
        <el-button class="submit-action-btn" type="danger">我要参加</el-button>
    </div>
</div>
</template>

<script>
var echarts = require('echarts');
import { Toast } from 'mint-ui';
import qiniu from './../config/qiniu.json'
import api from './../fetch/api.js';
import { formatDate } from './../components/date.js';

export default {
    filters: {                    //时间转换
        formatDate(time) {
            var date = new Date(time);
            return formatDate(date, 'yyyy年MM月dd日 hh时mm分');
        }
    },
  props: ['id'],
  data() {
    return {
      msg: 'Welcome to Your Vue.js App',
      qiniu: qiniu,
      dateAction: {}
    }
  },
  mounted() {
    this.drawLine();
    this.init();
  },
  methods: {
    init() {
         api.findAction(this.$route.query.id).then(res => {
            this.dateAction = res.data.data;
         })
    },
    //参加活动
    joinAction() {
        //TODO
        Toast({
            message: '参加成功',
            position: 'middle',
            duration: 5000,
            iconClass: 'icon-tip'
        });
    }
    ,
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let peopleCount = echarts.init(document.getElementById('peopleCount'))
      // 绘制图表
        peopleCount.setOption({
            title : {
            text: '帅哥美女人数',
            x:'center',
            textStyle: {
                color:'rgb(248, 128, 80)'
            },
        },

        tooltip: {},
        xAxis: {
          data: ["帅哥", "美女"]
        },
        yAxis: {},
        series: [{
          name: '人数',
          type: 'bar',
          color: ['pink', 'pink'],
          data: [5, 20]
        }]
      });

        // 基于准备好的dom，初始化echarts实例
        let peopleRatio = echarts.init(document.getElementById('peopleRatio'))
        const option = {
            title : {
                text: '帅哥美女比率',
                x:'center',
                textStyle: {
                    color:'rgb(248, 128, 80)'
                },
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)",
                textStyle: {
                    color:'rgb(248, 128, 80)',
                    size: '9px'
                },
            },
            calculable : true,
            series :[
                {
                    name:'男女比率',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:[
                        {value:335, name:'帅哥'},
                        {value:310, name:'美女'},
                    ]
                }
            ]
        };
        peopleRatio.setOption(option);
    }
  }
}
</script>

<style lang="scss">
    .action-detail {
        .action-detail-header {
            .mint-header {
                background: pink;
            }
        }
        .people-count-title {
            text-align: center;
            font-weight: bold;
            margin: 0;
            margin-top: 5px;
            padding: 0;
        }
        .people-count {
            width: 100%;
            height: 300px;
        }
        .people-ratio {
            width: 90%;
            height: 180px;
            display: block;
            margin: 0 auto;
        }
        .action-img {
            width: 100%;
            height: 30%;
        }
        .action-theme {
            ul {
                margin: 0;
                padding: 0;
                padding-left: 5px;
                padding-top:5px;
            }
            li {
                list-style: none;
                color: pink;
                font-size: 15px;
                margin: 5px;
            }
        }
        .submit-action-div {
            margin: 0 auto;
            width: 90%;
            .submit-action-btn {
                width: 100%;
            }
            .el-button--danger {
                margin: 10px 0px;
            }
        }
    }
</style>

