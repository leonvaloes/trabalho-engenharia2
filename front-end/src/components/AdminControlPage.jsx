import React, { useState, useEffect } from "react";
import axios from "axios";
import "./AdminControlPage.css";
import Header from "./header";
import Footer from "./footer";

function AdminControlPage() {
  const [users, setUsers] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    async function fetchUsers() {
      setIsLoading(true);
      try {
        const response = await axios.get("http://localhost:8080/auth/all-users");
        setUsers(response.data);
      } catch (error) {
        console.error("Error fetching users:", error);
      }
      setIsLoading(false);
    }
    fetchUsers();
  }, []);

  const handleDeactivateUser = async (userId) => {
    try {
      await axios.put(`http://localhost:8080/auth/deactivate-user/${userId}`);
      setUsers(users.map(user => {
        if (user.id === userId) {
          return { ...user, active: false };
        }
        return user;
      }));
    } catch (error) {
      console.error("Error deactivating user:", error);
    }
  };
  

  return (
    <>
      <Header />
      <div className="admin-container">
        <h1>Admin Control Page</h1>
        {isLoading ? (
          <p className="loading">Loading...</p>
        ) : (
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Role</th>
                <th>Status</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {users.map(user => (
                <tr key={user.id}>
                  <td>{user.id}</td>
                  <td>{user.username}</td>
                  <td>{user.role}</td>
                  <td>{user.active ? "Active" : "Inactive"}</td>
                  <td>
                    {user.active && (
                      <button onClick={() => handleDeactivateUser(user.id)}>Deactivate</button>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
      <Footer />
    </>
  );
}

export default AdminControlPage;
