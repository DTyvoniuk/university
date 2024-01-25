import React from 'react';
import { Link } from 'react-router-dom';
import './Navigation.css';

function Navigation() {
  return (
      <div>
        <nav>
          <ul>
            <li><Link to="/">Department</Link></li>
            <li><Link to="/lector">Lector</Link></li>
          </ul>
        </nav>
      </div>
  );
}

export default Navigation;
