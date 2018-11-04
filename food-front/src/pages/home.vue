<template>
    <div class="home main-view">
        <div class="home-top">
            <div class="home-header">
                <input class="search-key" v-model="searchKey"/>
                <div class="clear-block"></div>
                <div class="block">
                    <el-carousel height="150px">
                    <el-carousel-item v-for="(img,index) in imgs" :key="index">
                        <img :src="img.url"/>
                    </el-carousel-item>
                    </el-carousel>
                </div>
            </div>
            <div class="home-location" @click="changea('currentCity')">
                <img style="height: 30px;" class='home-localtion-svg' src="./../assets/img/location.svg" alt="" />
                <span>{{currentCity}}</span>
                <span>|</span>
                <span>遇到你想要的,守着你所心疼的</span>
            </div>
        </div>

        <div class="home-menu">
            <ul>
                <li v-for="item in 4" :key="item">
                <!--添加-->
                <div style="width:25%">
                    <router-link to="/add-action">
                        <img style="height: 30px;" class='home-add-svg' src="./../assets/img/add.svg" alt="ff" />
                    </router-link>
                </div>
                <div>添加</div>
                </li>
            </ul>
        </div>

        <div style="clear: both"></div>

        <div class="home-content">
            <md-list class="md-triple-line" v-for="(dateAction, index) in dateActions" :key="index">
                 <router-link :to="{path:'/detail',query:{id:dateAction.id}}" class="msg-detail">
                    <md-list-item class="home-md-list-item">
                        <md-avatar>
                            <img :src="qiniu.qiniu + '/' + dateAction.dateActionImg" alt="People">
                        </md-avatar>
                        <div class="md-list-item-text">
                            <span class="home-theme">{{dateAction.theme}}({{'举办方'}})</span>
                            <span>
                                
                                <span>人均 : {{dateAction.consumption}}</span>
                            </span>
                            <span>
                                <span>活动 : {{dateAction.introduction}}</span>
                            </span>
                        </div>
                        <div class="home-number">
                            <span>
                                <p><img class="home-love" src='./../assets/img/heart.svg'/> {{'9.0'}}</p>
                                <p>帅哥:{{'100'}}</p>
                                <p>美女:{{'200'}}</p>
                            </span>
                        </div>
                    </md-list-item>
                </router-link>
            </md-list>

        </div>

        <transition :name="showCity?'slide':'slide_back'">
            <v-city
                v-show="showCity"
                :Cdata="Cdata"
                :Hcity="Hcity"
                :Tcity="Tcity"
                :currentCity="currentCity">
            </v-city>
        </transition>
        <div id="allmap"></div>
    </div>
</template>

<script>
import {MP} from './map.js'
import { mapMutations, mapState, mapActions } from 'vuex';
import { Hcity, Cdata } from './../assets/js/city.data';
import vCity from '../components/city';
import api from './../fetch/api.js';
import qiniu from './../config/qiniu.json';
import { Toast } from 'mint-ui';

export default {
    data() {
        return {
            searchKey: '',
            Hcity,
            Cdata,
            Tcity: '',
            dateActions: {},
            qiniu: qiniu,
            imgs: [
                {url:require('./../assets/img/1.jpeg')},
                {url:require('./../assets/img/2.jpeg')},
                {url:require('./../assets/img/3.jpeg')},
                {url:require('./../assets/img/4.jpeg')},
            ]
        }

    },
    computed: {
        ...mapState('base', ['showCity', 'startCity', 'endCity','currentCity','showDetial']),
    },
    methods: {
        ...mapMutations('base',['changeStaticState']),
        changea(val) {
            this.changeStaticState({
                name: 'showCity',
                newVal: true
            });
            this.Tcity = val;
        },
        showDetialsdf(val) {
            this.changeStaticState({
                name: 'showDetial',
                newVal: true
            });
        },
        search() {
            this.$toast({
                message: '请求超时',
            })
        },
        locationCity() {
            var vm = this
            var map = new BMap.Map("allmap")
            var point = new BMap.Point(116.331398,39.897445)
            map.centerAndZoom(point,12)

            function myFun(result){
                var cityName = result.name
                map.setCenter(cityName)

                vm.changeStaticState({
                    name:'currentCity',
                    newVal: cityName
                })
            }
            var myCity = new BMap.LocalCity()
            myCity.get(myFun)
        },
        findAll() {
            api.findAllAction().then(res => {
                if (res.data.status == 0) {
                    this.dateActions = res.data.data
                } else {
                    Toast({
                        message: '暂时没有发布信息',
                        position: 'middle',
                        duration: 5000,
                        iconClass: 'icon-tip'
                    });
                }
            })
        }
    },
    mounted: function() {
        this.changeStaticState({
            name: 'link',
            newVal: '/'
        }),
        this.$nextTick(function(){
            MP('amczsAgEUKG9F893UGTxkYBK2onrCWGf').then(BMap => {
                this.locationCity();
            })
        }),
        this.findAll();
    },
    components: {
        vCity
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
@import './../assets/css/style.scss';

#app {
    white-space: nowrap;
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
}

.slide-enter,
.slide_back-enter {
    position: absolute;
    width: 100%;
}

.slide-leave,
.slide_back-leave {
    position: absolute;
    width: 100%;
}

.slide-enter-active,
.slide_back-enter-active {
    transition: transform .3s ease-out;
}

.slide-leave-active {
    position: absolute;
    z-index: -1;
    transition: transform .6s ease-out;
    transform: translate3d(-50%, 0, 0);
}

.slide-enter {
    transform: translate3d(100%, 0, 0);
}

.slide_back-leave-active {
    position: absolute;
    transition: transform .3s ease-out;
    z-index: 99;
    transform: translate3d(100%, 0, 0); // box-shadow: -4px 0 10px -3px rgba(0,0,0,.4);
}

.slide_back-enter {
    transition: transform .2s ease-out;
    transform: translate3d(-30%, 0, 0);
}
.main-view {
    position: absolute;
    background: #fff;
    height: 100%;
    width: 100%;
}

.mint-indicator-wrapper {
    position: absolute;
}
.city-view{
    width: 100%;
    height: 100%;
    background: #fff;
}
.mint-header{
    z-index: 99;
}
.mint-search-list{
    padding-top: 40px;
}
.mint-search{
    height: 40px;
}
.home-localtion-svg{
    margin-left: 5px;
    width: 20px;
}
.main-view{ 
  .clear-block {
      clear: both;
  }
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
     background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
     background-color: #d3dce6;
  }
    .home-header{
        white-space: nowrap;
        color: pink;
        span{
            margin-left: 5px;
            display: block;
            float: left;
            height: 40px;
            line-height: 35px;
            color: pink;
            font-size: 2rem;
        }
        .mint-search {
            float: left;
        }
        .search-key {
            display: block;
            height: 26px;
            padding: 2px 15px;
            padding-bottom: 4px;
            border: 1 solid pink;
            border-radius: 62px;
            outline:none;
            z-index: 9999;
            position: absolute;
            filter: alpha(opacity=50);
            -moz-opacity: .5;
            opacity: .5;
            color: black;
            right: 5%;
            top: 5px;
            width: 90%;
        }
        .home-add-svg {
            display: block;
            float: left;
            height: 30px;
            margin-left: 5px;
            margin-top: 2px;
        }
    }
    .home-top {
        position:sticky;
        top: 0; /* 阈值 */
        z-index: 999;
        background: #fff;
    }
    .home-md-list-item {
        border-bottom: 0.5px solid rgba(252, 188, 246, 0.644);
    }
    .home-location {
        clear: both;;
        float: none;
        color: pink;
        white-space: nowrap;
    }
    .home-content {
        .msg-detail {
            color: pink;
        }
        .home-theme {
            font-weight: bold;
        }
        .home-number {
            font-size: 10px;
            margin-right: 5px;
            p {
                margin: 0;
            }
        }
        .home-love {
            width: 15px;
            height: 15px;;
        }
    }
    .home-menu {
        ul {
            list-style:none;
            color: pink;
            padding: 0;
            li {
                float: left;
                margin: 0 25px;
                img {
                    max-width: 30px; 
                }
            }
        }
    }
}
</style>
