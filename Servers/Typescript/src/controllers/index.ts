import { Request, Response } from 'express'
import { addUser,deleteUser, deleteUserByName, getUserByName, getAllUsers, getUserById, updateUserById, usersModel } from '../model'

const bottleNeck = async (req: Request, res: Response) => {
    try{
        res.status(200).json({working_status: 'working'}).end()
    }catch(err){
        console.log(err)
        return res.sendStatus(404)
    }
}

const registerUser = async (req: Request, res: Response) => {

    try{
        const {name, country_code, birthday, age} = req.body
        // check the property to not be null
        if(!name || !country_code || !birthday || !age){
            return res.sendStatus(400)
        }
        const existingUser = await getUserByName(name)
        if(existingUser){
            return res.sendStatus(400)
        }
        // create and save the new user
        const user = await addUser({name, country_code, birthday, age})
        return res.status(200).json(user).end()
    }catch(error){
        console.log(error)
        return res.sendStatus(404)
    }
}


const showAllUsers = async(req: Request, res: Response) => {
    try{
        const users = await getAllUsers()
        if(!users){
            return res.sendStatus(404)
        }
        res.status(200).json(users).end()
    }catch(err){
        console.log(err)
        return res.sendStatus(404)
    }
}


const showUserByName = async(req: Request, res: Response) => {
    let {username} = req.params;
    try{
        const user = await getUserByName(username)
        res.status(200).json(user).end()
    }catch(err) {
        console.log(err)
        return res.sendStatus(404)
    }

}

const updateAnyUser = async (req: Request, res: Response) =>{
    try{
        let {id} = req.params
        let {name,country_code, birthday, age} = req.body
        const user = await getUserById(id)
        if(!user){
            return res.sendStatus(404);
        }
        await updateUserById(id, {name, country_code, birthday, age})
        res.sendStatus(200)
    }catch(err){
        console.log(err)
        return res.sendStatus(404)
    }
}

const deleteUserId = async(req:Request, res:Response) => {
    try{
        let{id} = req.params
        await deleteUser(id)
        return res.sendStatus(200)
    }catch(err) {
        console.log(err)
        return res.sendStatus(404)
    }
}

const deleteUserName = async(req:Request, res:Response) => {
    try{
        let{name} = req.params
        await deleteUserByName(name)
        return res.sendStatus(200)
    }catch(err) {
        console.log(err)
        return res.sendStatus(404)
    }
}

export {registerUser, showAllUsers, bottleNeck, showUserByName, updateAnyUser, deleteUserId, deleteUserName}