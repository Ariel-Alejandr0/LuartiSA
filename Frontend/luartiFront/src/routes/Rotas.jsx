import React from 'react'
import Login from '../screens/Login'
import { Route, Routes } from 'react-router-dom'
import CadastroDevs from '../screens/CadastroDevs'
import Tarefas from '../screens/Tarefas'
import CadastroTipoTarefa from '../screens/CadastroTipoTarefa'

export default function Rotas() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route path='/cadastroDevs' element={<CadastroDevs/>}/>
      <Route path='/tarefas' element={<Tarefas/>}/>
      <Route path='/cadastroTipoTarefas' element={<CadastroTipoTarefa/>}/>
    </Routes>
  )
}
