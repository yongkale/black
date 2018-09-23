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
        <img class="action-img" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538291894&di=9ea7a29ed5ffa06b84081eec13dcca64&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fbba1cd11728b47104def00f0c8cec3fdfd0323ce.jpg">
        <div class="action-theme">
            <ul>
                <li>活动主题:</li>
                <li>活动介绍:</li>
                <li>活动举办方:</li>
            </ul>
        </div>
    </div>
    <div id="peopleCount" class="people-count"></div>
    <div id="peopleRatio" class="people-ratio"></div>
</div>
</template>

<script>
var echarts = require('echarts');

export default {
  data() {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  mounted() {
    this.drawLine();
  },
  methods: {
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
            series : [
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
    }
</style>

