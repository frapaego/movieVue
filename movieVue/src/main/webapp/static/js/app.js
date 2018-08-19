Vue.use(VueRouter);
//Vue.config.debug = true;

Vue.http.options.emulateJSON = true;

var Home = Vue.extend({
    template:'#tableTemplate',
    data:function(){
        this.$http.get("api/movies/getAllMovies.json").then(function(response){
            this.$set('movies',response.data);
        }).catch(function(ex){
            alert("Error al obtener los datos del lado del servidor: " + ex.message);
        });
        return {
            movies:[]
        };
    }
});

var ViewMovie = Vue.extend({
    template:"#movieTemplate",
    data:function(){
        this.$http.get("api/movies/getMovieById/"+this.$route.params.movieId+".json").then(function(response){
            this.$set('movie',response.data);
        }).catch(function(ex){
            alert("Error al obtener los datos：" + ex.message);
        });
        return {
            movie:{}
        };
    },
    methods:{
        remove:function(movie){
            if(confirm("¿Seguro que quieres eliminar este video?")) {
                this.$http.delete("api/movies/deleteMovie/"+movie.id).then(function(response){
                    if(response.data == "success") {
                        router.replace({path:'/'});
                    }
                }).catch(function(ex){
                    alert("Error al eliminar los datos: " + ex.message);
                });
            }
        },
        edit:function(movie){
            router.go({name:'editMovie',params:{"movieId":movie.id}});
        }
    }
});

var NewMovie = Vue.extend({
    template:"#newTemplate",
    data:function(){
        return {
            movie:{}
        };
    },
    methods:{
        save:function(){
            this.$http.post("api/movies/createMovie",this.movie).then(function(response){
                if(response.data) {
                    router.replace({name:'movieView',params:{movieId:response.data.id}});
                }
            }).catch(function(ex){
                alert("Error al guardar los datos: " + ex.message);
            });
        }
    }
});
var EditMovie = Vue.extend({
    template:"#editTemplate",
    data:function(){
        this.$http.get("api/movies/getMovieById/"+this.$route.params.movieId+".json").then(function(response){
            this.$set("movie",response.data);
        }).catch(function(ex){
            alert("Error al obtener los datos：" + ex.message);
        });
        return {
            movie:{}
        };
    },
    methods:{
        save:function(){
            this.$http.post("api/movies/updateMovie",this.movie).then(function(response){
                if(response.data == "success") {
                    router.replace({name:'movieView',params:{movieId:this.movie.id}});
                }
            }).catch(function(ex){
                alert("Error al guardar los datos：" + ex.message);
            });
        }
    }
});



var App = Vue.extend({});
var router = new VueRouter();
router.map({
    '/':{
        component:Home
    },
    '/movie/:movieId':{
        name:'movieView',
        component:ViewMovie
    },
    '/new':{
        component:NewMovie
    },
    '/edit/:movieId':{
        name:"editMovie",
        component:EditMovie
    }
});
router.start(App, '#app');
