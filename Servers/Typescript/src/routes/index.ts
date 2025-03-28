import { registerUser, showAllUsers, bottleNeck, showUserByName, deleteUserId, updateAnyUser } from '../controllers'
import {Router} from 'express'

const router = Router()

export default () => {
    // Base url
    router.get('/', async(req, res) => {bottleNeck(req, res)})
    // create user
    router.post('/register', async(req, res) => {registerUser(req, res)})
    // read all user
    router.get('/users', async(req, res) => {showAllUsers(req, res)})
    // read only one user
    router.get('/users/:username', async(req, res) => {showUserByName(req, res)})
    // update user
    router.put('/users/:id', async(req , res) => {updateAnyUser(req, res)})
    // delete user
    router.delete('/users/:id', async(req, res) => {deleteUserId(req, res)})
    return router
}
