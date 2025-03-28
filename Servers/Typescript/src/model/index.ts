import mongoose from "mongoose";

interface User{
    name: string;
    country_code: string;
    birthday: Date;
    age: number;
}

const UserSchema = new mongoose.Schema({
    name: {type: String, required: true},
    country_code: {type: String, required: true},
    birthday: {type: Date, required: true},
    age: {type: Number, required: true}
})

UserSchema.set('autoIndex', false)

const usersModel = mongoose.model('users', UserSchema)

// Functions to make the access easier
const getUserByName = (name:string) => usersModel.findOne({name})
const getUserById = (id:string) => usersModel.findById(id)
const getUserByCountry = (code: string) => usersModel.find({'country_code': code})
const addUser = (values: Record<string, User>) => new usersModel(values).save().then(user => user.toObject()) 
const deleteUser = (id: string) => usersModel.findOneAndDelete({_id: id}) 
const deleteUserByName = (name: string) => usersModel.findOneAndDelete({name})
const updateUserById = (id: string, values: Record<string, User>) => usersModel.findByIdAndUpdate(id, values) 
const getAllUsers = () => usersModel.find({})

export {User, usersModel, getUserByCountry, getUserByName,addUser, updateUserById, deleteUser, deleteUserByName, getAllUsers, getUserById}
