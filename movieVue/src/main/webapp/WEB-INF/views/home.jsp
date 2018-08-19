<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
</head>
<body >
    <div class="container" id="app">
        <router-view></router-view>
    </div>

<script src="static/js/vue.js"></script>
<script src="static/js/vue-router.min.js"></script>
<script src="static/js/vue-resource.min.js"></script>
<script type="vue/template" id="tableTemplate">
    <a v-link="{path:'/new'}" class="btn btn-success">Agregar un nuevo video</a>
        <table class="table">
            <thead>
            <tr>
                <th>Nombre de la película</th>
                <th width="50">Calificación</th>
                <th width="150">Director</th>
                <th width="100">Año de lanzamiento</th>
                <th width="100">Tiempo de liberación</th>
            </tr>
            </thead>
            <tbody>
            <template v-for="movie in movies.content">
                <tr>
                    <td><a v-link="'/movie/'+movie.id">{{movie.title}}</a></td>
                    <td>{{movie.rate}}</td>
                    <td>{{movie.daoyan}}</td>
                    <td>{{movie.releaseyear}}</td>
                    <td>{{movie.sendtime}}</td>
                </tr>
            </template>
            </tbody>
        </table>
</script>
<script type="vue/template" id="movieTemplate">
    <h3>{{movie.title}} <small>{{movie.rate}}</small></h3>
    <hr>
    Año de lanzamiento：{{movie.releaseyear}} Tiempo de liberación：{{movie.sendtime}} Director：{{movie.daoyan}}
    <hr>
    <p>{{{movie.jianjie}}}</p>
    <a @click="remove(movie)" class="btn btn-danger">Eliminar</a>
    <button class="btn btn-success" @click="edit(movie)">Editar</button>
</script>
<script type="vue/template" id="newTemplate">
    <form>
        <legend>Agregar un nuevo video</legend>
        <label>Nombre de la película</label>
        <input type="text" v-model="movie.title">
        <label>Calificación</label>
        <input type="text" v-model="movie.rate">
        <label>Director</label>
        <input type="text" v-model="movie.daoyan">
        <label>Año de lanzamiento</label>
        <input type="year" v-model="movie.releaseyear">
        <label>Fecha de lanzamiento</label>
        <input type="date" v-model="movie.sendtime">
        <label>Introducción</label>
        <textarea v-model="movie.jianjie" style="height: 100px;width: 90%"></textarea>
        <div class="form-actions">
            <button class="btn btn-primary" type="button" @click="save()">Guardar</button>
        </div>
    </form>
</script>
<script type="vue/template" id="editTemplate">
    <form>
        <legend>Editando una película</legend>
        <input type="hidden" v-model="movie.id">
        <label>Nombre de la película</label>
        <input type="text" v-model="movie.title">
        <label>Calificación</label>
        <input type="text" v-model="movie.rate">
        <label>Director</label>
        <input type="text" v-model="movie.daoyan">
        <label>Año de lanzamiento</label>
        <input type="text" v-model="movie.releaseyear">
        <label>Fecha de lanzamiento</label>
        <input type="text" v-model="movie.sendtime">
        <label>Introducción</label>
        <textarea v-model="movie.jianjie" style="height: 100px;width: 90%"></textarea>
        <div class="form-actions">
            <button class="btn btn-primary" type="button" @click="save()">Guardar</button>
        </div>
    </form>
</script>
<script src="static/js/app.js"></script>
</body>
</html>