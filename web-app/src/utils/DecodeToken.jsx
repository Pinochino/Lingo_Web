import { jwtDecode } from "jwt-decode";

export const decodeToken = (token) => {
  try {
    const decodeInf = jwtDecode(token);
    const {
      preferred_username,
      email,
      sub,
    } = decodeInf;

    const roles =
      decodeInf?.resource_access?.["realm-management"]?.roles || ["NORMAL_USER"];

    const userInf = { preferred_username, email, sub, roles };

    return userInf;
  } catch (err) {
    console.error("Error when decode token", err);
  }
};
