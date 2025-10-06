import { useState } from 'react'
import './App.css'
import './styles/antStyle.css'
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import AdminLayout from './layouts/AdminLayout';

import 'react-toastify/dist/ReactToastify.css';

import AuthLayout from './layouts/AuthLayout';
import ClientLayout from './layouts/ClientLayout';
import HomePage from './pages/HomePage';
import CreateTestPage from './pages/admin/CreateTestPage';
import UserPage from './pages/admin/UserPage';
import TestPage from './pages/admin/TestPage';
import DashboardPage from './pages/admin/DashboardPage';


import BeforeTestPage from './pages/tests/BeforeTestPage';
import AfterTestPage from './pages/tests/AfterTestPage';
import HavingTestPage from './pages/tests/HavingTestPage';
import LoginPage from './pages/auth/LoginPage';
import RegisterPage from './pages/auth/RegisterPage';
import ForgetPage from './pages/auth/ForgetPage';
import ResetPage from './pages/auth/ResetPage';
import { ToastContainer } from 'react-toastify';
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { initializeAuth } from "./slice/authentication";
import TestListPage from './pages/tests/TestListPage';
import ProtectedRoute from './components/share/ProtectedRoute';
import NotFound from './components/share/NotFound';
import { Spin } from 'antd';
function App() {

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(initializeAuth());
  }, [dispatch]);


  const router = createBrowserRouter([
    {
      path: "/admin",
      element: (
        <ProtectedRoute>
          <AdminLayout />
        </ProtectedRoute>
      ),
      errorElement: <NotFound />,
      children: [
        {
          index: true,
          element: (
            <ProtectedRoute>
              <DashboardPage />
            </ProtectedRoute>
          )
        },
        {
          path: "user",
          element: <UserPage />
        },
        {
          path: "test",
          element: <TestPage />
        }, {
          path: "createTest",
          element: <CreateTestPage />
        }
      ]
    },
    {
      path: "/auth",
      element: <AuthLayout />,
      errorElement: <NotFound />,
      children: [
        {
          path: "login",
          element: <LoginPage />,
        },
        {
          path: "register",
          element: <RegisterPage />,
        },
        {
          path: "forget",
          element: <ForgetPage />,
        },
        {
          path: "reset",
          element: <ResetPage />,
        }
      ],
    },
    {
      path: "/",
      element: <ClientLayout />,
      errorElement: <NotFound />,
      children: [
        {
          index: true,
          element: <HomePage />,
        },
        {
          path: "tests",
          element: <TestListPage />,
        },
        {
          path: "tests/:id/:name",
          element: <BeforeTestPage />,
        },
        {
          path: "tests/:id/:name/results/:attemptId",
          element: <AfterTestPage />,
        },

        {
          path: "tests/:id/:name/doTests",
          element: <HavingTestPage />,
        },
      ],
    },
  ]);

  return (
    <>
      <RouterProvider router={router} />
      <ToastContainer
        position="top-right"
        autoClose={3000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        pauseOnHover
        draggable
        theme="colored"
      />
    </>
  );
}

export default App
