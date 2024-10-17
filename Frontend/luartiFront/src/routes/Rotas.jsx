import React from 'react'
import Login from '../screens/Login'
import { Route, Routes } from 'react-router-dom'
import CadastroDevs from '../screens/CadastroDevs'

export default function Rotas() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route path='/cadastroDevs' element={<CadastroDevs/>}/>
    </Routes>
  )
}
