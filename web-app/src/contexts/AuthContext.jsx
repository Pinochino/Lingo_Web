import { createContext, useEffect, useState } from "react";
import axios from "axios";
import { toast } from "react-toastify";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [isAuthenticated, setIsAuthenticated] = useState(false);


  useEffect(() => {
    const checkKeycloak = async () => {
      try {
        const token = localStorage.getItem("access_token");
        if (token) {
          setIsAuthenticated(true);
        } else {
          setIsAuthenticated(false);
        }
      } catch (error) {
        console.log("Cant't authorize user: " + error);
      } finally {
        setLoading(false);
      }
    }
    checkKeycloak();
  }, [])


  const login = async (username, password) => {
    try {
      setLoading(true);

      const response = await axios.post(
        `http://localhost:8080/api/v1/auth/login`, { username, password }, {
        withCredentials: true
      }
      )

      const { access_token } = response.data;

      localStorage.setItem("access_token", access_token);

      //set default for each time call api by axios
      axios.defaults.headers.common['Authorization'] = `Bearer ${access_token}`
      // logic get user info
      const userInfo = await axios.get(
        `http://localhost:8180/realms/Lingo/protocol/openid-connect/userinfo`,
        {
          headers: {
            "Authorization": `Bearer ${access_token}`
          }
        }
      )

      setUser(userInfo.data);
      localStorage.setItem('user_name', userInfo.data.name);
      setIsAuthenticated(true);
      toast.info("Đăng nhập thành công")
      return true;
    } catch (err) {
      console.log("Login failed with error: ", err?.message);
      toast.error(err?.response?.data?.detail);
      return false;
    }
    finally {
      setLoading(false);
    }
  };

  const register = async (userData) => {
    try {
      setLoading(true);

      console.log(userData);
      const response = await axios.post(
        `http://localhost:8080/api/v1/account`,
        userData
      )
      toast.info("Đăng ký tài khoản thành công")
      return true;

    } catch (err) {
      console.error("Error when creating new account:" + err);
      toast.error(err?.response?.data?.detail);
      return false;
    } finally {
      setLoading(false);
    }
  }

  const logout = () => {
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');
    localStorage.removeItem('user_name');
    delete axios.defaults.headers.common['Authorization'];
    setIsAuthenticated(false);
    setUser(null);
  }


  return (
    <AuthContext.Provider value={{ user, loading, login, logout, isAuthenticated, register }}>
      {children}
    </AuthContext.Provider>
  );

}

export default AuthContext;