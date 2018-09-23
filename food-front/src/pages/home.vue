<template>
    <div class="home main-view">
        <div class="home-top">
            <div class="home-header">
                <span>相约</span>
                <input class="search-key" v-model="searchKey"/>
            </div>

            <div class="home-location" @click="changea('currentCity')">
                <img style="height: 30px;" class='home-localtion-svg' src="./../assets/img/location.svg" alt="ff" />
                <span>{{currentCity}}</span>
                <span>|</span>
                <span>遇到你想想要的,守着你所心疼的</span>
            </div>
        </div>

        <div class="home-content">
            <md-list class="md-triple-line">
                 <router-link to="/detail" class="msg-detail">
                    <md-list-item class="home-md-list-item">
                        <md-avatar>
                            <img src="https://placeimg.com/40/40/people/1" alt="People">
                        </md-avatar>

                        <div class="md-list-item-text">
                            <span class="home-theme">{{'主题'}}({{'举办方'}})</span>
                            <span>
                                <span>人均 : {{'80'}}</span>
                            </span>
                            <span>
                                <span>活动 : {{'活动'}}</span>
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
import { Hcity, Cdata } from './../assets/js/city.data'
import vCity from '../components/city';

export default {
    data() {
        return {
            searchKey: '',
            Hcity,
            Cdata,
            Tcity: '',
        }

    },
    computed: {
        ...mapState('base', ['showCity', 'startCity', 'endCity','currentCity','showDetial'])
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
            var vm = this;
            var map = new BMap.Map("allmap");
            var point = new BMap.Point(116.331398,39.897445);
            map.centerAndZoom(point,12);

            function myFun(result){
                var cityName = result.name;
                map.setCenter(cityName);

                vm.changeStaticState({
                    name:'currentCity',
                    newVal: cityName
                })
            }
            var myCity = new BMap.LocalCity();
            myCity.get(myFun); 
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
        })
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
    .home-header{
    color: pink;
        padding-top: 5px;
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
            margin-left: 20px;
            height: 35px;
            padding: 3px 10px;
            float: left;
            border: 1 solid pink;
            border-radius: 62px;
            outline:none;
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
}
</style>
