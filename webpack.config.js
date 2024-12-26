const path = require('path');

module.exports = {
    entry: './src/main/resources/static/js/app.js', // Входной файл JS
    output: {
        filename: 'bundle.js',  // Имя итогового файла
        path: path.resolve(__dirname, 'src/main/resources/static/dist'),  // Путь к выходной папке
        publicPath: '/static/dist/',  // Убедитесь, что используете публичный путь
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader'],  // Загрузка стилей
            },
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',  // Транспиляция JS через Babel
                },
            },
        ],
    },
    devServer: {
        static: path.join(__dirname, 'src/main/resources/static/dist'),  // Путь к папке для статических файлов
        compress: true,
        port: 9000,
        historyApiFallback: true, // Для поддержки истории API (например, для React)
    },
    mode: 'development',
};
