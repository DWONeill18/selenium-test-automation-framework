import React, { useState } from "react";

const theme = {
  bg: "#0b0f1a",
  panel: "#111827",
  panel2: "#0f172a",
  border: "#1f2a44",
  text: "#e5e7eb",
  muted: "#94a3b8",
  blue: "#3b82f6",
  blueDark: "#2563eb"
};

export default function App() {
  const [page, setPage] = useState("login");
  const [user, setUser] = useState(null);

  const validUsers = [
    { username: "admin", password: "admin123" },
    { username: "tester", password: "test123" },
  ];

  const handleLogin = (username, password) => {
    const found = validUsers.find(
      (u) => u.username === username && u.password === password
    );
    if (found) {
      setUser(found.username);
      setPage("form");
    } else {
      alert("Invalid credentials");
    }
  };

  const isAdmin = user === "admin";

  if (page === "login") {
    return <LoginPage onLogin={handleLogin} />;
  }

  return (
    <div style={{ background: theme.bg, minHeight: "100vh", color: theme.text, padding: 16 }}>
      <nav style={{ display: "flex", gap: 10, marginBottom: 16 }}>
        <button data-testid="nav-form" onClick={() => setPage("form")} style={navBtn}>Form</button>
        <button data-testid="nav-table" onClick={() => setPage("table")} style={navBtn}>Table</button>
        <button
          data-testid="nav-admin"
          onClick={() => setPage("admin")}
          disabled={!isAdmin}
          style={{ ...navBtn, opacity: isAdmin ? 1 : 0.4 }}
        >
          Admin Page
        </button>
        <button data-testid="logout" onClick={() => { setUser(null); setPage("login"); }} style={navBtn}>Logout</button>
      </nav>

      {page === "form" && <FormPage />}
      {page === "table" && <TablePage />}
      {page === "admin" && <AdminPage />}
    </div>
  );
}

const navBtn = {
  padding: "8px 12px",
  border: "1px solid #1f2a44",
  borderRadius: 6,
  background: "#0f172a",
  color: "#e5e7eb",
  cursor: "pointer"
};

function LoginPage({ onLogin }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const submit = () => {
    if (
      (username === "admin" && password === "admin123") ||
      (username === "tester" && password === "test123")
    ) {
      onLogin(username, password);
    } else {
      setError("Login failed");
    }
  };

  return (
    <div style={{
      display: "flex",
      justifyContent: "center",
      alignItems: "center",
      minHeight: "100vh",
      background: theme.bg
    }}>
      <div style={{
        width: 380,
        padding: 28,
        borderRadius: 12,
        border: `1px solid ${theme.border}`,
        background: theme.panel,
        display: "flex",
        flexDirection: "column",
        gap: 12
      }}>
        <div style={{ textAlign: "center" }}>
          <h1 style={{ margin: 0, color: theme.blue }}>Selenium Practice App</h1>
          <h2 style={{ margin: 0, color: theme.text }}>Login</h2>
        </div>

        {error && <div data-testid="login-error" style={{ color: "#f87171", textAlign: "center" }}>{error}</div>}

        <input data-testid="username" placeholder="Username" value={username}
          onChange={(e) => setUsername(e.target.value)} style={inputStyle} />

        <input data-testid="password" type="password" placeholder="Password" value={password}
          onChange={(e) => setPassword(e.target.value)} style={inputStyle} />

        <button data-testid="login-btn" onClick={submit} style={primaryBtn}>Login</button>
      </div>
    </div>
  );
}

const inputStyle = {
  padding: 10,
  borderRadius: 6,
  border: `1px solid ${theme.border}`,
  background: theme.panel2,
  color: theme.text
};

const primaryBtn = {
  padding: 10,
  borderRadius: 6,
  border: "1px solid #1f2a44",
  background: theme.blue,
  color: "white",
  cursor: "pointer"
};

function FormPage() {
  const [priority, setPriority] = useState("low");
  const [interests, setInterests] = useState([]);
  const [country, setCountry] = useState("UK");
  const [showSuccess, setShowSuccess] = useState(false);
  const [useAlert, setUseAlert] = useState(true);

  const toggleInterest = (val) => {
    setInterests((prev) =>
      prev.includes(val) ? prev.filter((i) => i !== val) : [...prev, val]
    );
  };

  const submit = () => {
    if (useAlert) alert("Form submitted successfully");
    setShowSuccess(true);
    setTimeout(() => setShowSuccess(false), 2000);
  };

  const card = {
    padding: 12,
    border: `1px solid ${theme.border}`,
    borderRadius: 8,
    background: theme.panel
  };

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: 16, maxWidth: 600 }}>
      <h2>Form</h2>

      <div style={card}>
        <label>
          <input data-testid="toggle-alert" type="checkbox" checked={useAlert}
            onChange={() => setUseAlert(!useAlert)} /> Use browser alert
        </label>
      </div>

      {showSuccess && <div data-testid="form-success" style={{ color: theme.blue }}>Form submitted successfully</div>}

      <div style={card}>
        <h4>Priority</h4>
        {['low','medium','high'].map(v => (
          <label key={v} style={{ marginRight: 10 }}>
            <input type="radio" checked={priority===v} onChange={()=>setPriority(v)} /> {v}
          </label>
        ))}
      </div>

      <div style={card}>
        <h4>Interests</h4>
        {['Sports','Music','Coding'].map(i => (
          <label key={i} style={{ marginRight: 10 }}>
            <input type="checkbox" onChange={() => toggleInterest(i)} /> {i}
          </label>
        ))}
      </div>

      <div style={card}>
        <h4>Country</h4>
        <select value={country} onChange={(e)=>setCountry(e.target.value)} style={inputStyle}>
          <option>UK</option><option>USA</option><option>India</option>
        </select>
      </div>

      <button onClick={submit} style={primaryBtn}>Submit</button>
    </div>
  );
}

function TablePage() {
  const initialData = Array.from({ length: 12 }).map((_, i) => ({
    id: i + 1,
    name: `User ${i + 1}`,
    status: i % 2 === 0 ? "Active" : "Inactive",
  }));

  const [data, setData] = useState(initialData);
  const [pageIndex, setPageIndex] = useState(0);
  const pageSize = 5;

  const paginated = data.slice(pageIndex * pageSize, pageIndex * pageSize + pageSize);
  const totalPages = Math.ceil(data.length / pageSize);

  const cellStyle = {
    padding: "12px 10px",
    textAlign: "left",
    verticalAlign: "middle"
  };

  const editRow = (row) => {
    const name = prompt("New name", row.name);
    if (!name) return;
    const status = prompt("Status", row.status);
    if (!status) return;

    setData(prev => prev.map(r => r.id === row.id ? { ...r, name, status } : r));
  };

  const deleteRow = (id) => {
    setData(prev => prev.filter(r => r.id !== id));
  };

  const tableCard = {
    background: theme.panel,
    border: `1px solid ${theme.border}`,
    borderRadius: 8,
    padding: 12
  };

  const btn = {
    padding: "6px 10px",
    borderRadius: 6,
    border: `1px solid ${theme.border}`,
    background: theme.panel2,
    color: theme.text,
    cursor: "pointer"
  };

  return (
    <div>
      <h2>Table</h2>

      <div style={tableCard}>
        <table style={{ width: "100%", borderCollapse: "collapse", tableLayout: "fixed" }}>
          <thead>
            <tr style={{ color: theme.muted, background: theme.panel2 }}>
              <th style={cellStyle}>ID</th>
              <th style={cellStyle}>Name</th>
              <th style={cellStyle}>Status</th>
              <th style={cellStyle}>Action</th>
            </tr>
          </thead>
          <tbody>
            {paginated.map(r => (
              <tr key={r.id} style={{ borderTop: `1px solid ${theme.border}` }}>
                <td style={cellStyle}>{r.id}</td>
                <td style={cellStyle}>{r.name}</td>
                <td style={cellStyle}>{r.status}</td>
                <td style={cellStyle}>
                  <button style={btn} onClick={() => editRow(r)}>Edit</button>
                  <button style={{ ...btn, marginLeft: 8 }} onClick={() => deleteRow(r.id)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        <div style={{ display: "flex", gap: 10, marginTop: 10, alignItems: "center" }}>
          <button style={btn} disabled={pageIndex===0} onClick={()=>setPageIndex(p=>p-1)}>Prev</button>
          <span>{pageIndex+1}/{totalPages}</span>
          <button style={btn} disabled={pageIndex===totalPages-1} onClick={()=>setPageIndex(p=>p+1)}>Next</button>
        </div>
      </div>
    </div>
  );
}

function AdminPage() {
  return (
    <div>
      <h2 style={{ color: theme.blue }}>Admin Page</h2>
      <div>Restricted admin content</div>
    </div>
  );
}


