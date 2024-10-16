import React from 'react'
import Login from '../screens/Login'
import { Route, Routes } from 'react-router-dom'

export default function Rotas() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
    </Routes>
  )
}
