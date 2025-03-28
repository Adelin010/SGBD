import express from 'express'
import http from 'http'
import cors from 'cors'
import mongoose from 'mongoose'
import routes from './routes'

const DB_URI = 'mongodb://localhost:27017/BankSystem'

const app = express()
app.use(express.json())
app.use(express.urlencoded())
const serve = http.createServer(app)
app.use(cors({credentials: true}))

serve.listen(8080, () => {
    console.log('Listening on Port: 8080')
})

mongoose.Promise = Promise
mongoose.connect(DB_URI)
// treat the error when connecting 
mongoose.connection.on('error', (error: Error) => console.log(error))

app.use('/', routes())