import {useEffect, useState} from "react";
import LectorApi from "../../api/LectorApi";
import LectorTable from "./LectorTable";
import '../../component/InputContainer.css'
import NewLectorModal from "./NewLectorModal";

function Lector() {
  const [lector, setLector] = useState(null);
  const [searchQuery, setSearchQuery] = useState(null);
  const [isModalOpen, setModalOpen] = useState(false);

  const openModal = () => {
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
  };

  useEffect(() => {
      getAllLectors();
  }, []);

  return(
      <>
        <div className='input-container'>
          <input onChange={handleInputChange} />
          <button onClick={fetchQueriedLectors}>Find</button>
          <button onClick={openModal}>Create Lector</button>
          <NewLectorModal isOpen={isModalOpen} closeModal={closeModal} fetchTableData={fetchQueriedLectors} />
        </div>
        {lector && <LectorTable lectors={lector} fetchTableData={fetchQueriedLectors} />}
      </>
  );

  function fetchQueriedLectors() {
    if (searchQuery && searchQuery.trim() !== '') {
      LectorApi.searchByQuery(searchQuery).then(({ data }) => {
        setLector(data);
      })
    } else {
      getAllLectors();
    }
  }

  function getAllLectors() {
    LectorApi.getAll().then(({ data }) => {
      setLector(data);
    });
  }

  function handleInputChange(event) {
    setSearchQuery(event.target.value);
  }
}

export default Lector;
