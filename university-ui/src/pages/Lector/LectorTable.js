import {DataGrid} from "@mui/x-data-grid";
import {Box} from "@mui/material";
import LectorApi from "../../api/LectorApi";
import { NotificationManager } from "react-notifications";
import '../../component/InputContainer.css'

function LectorTable({ lectors, fetchTableData }) {
  const columns = [
    {field: 'fullName', headerName: 'Full Name', sortable: false, editable: false, width: 250},
    {field: 'degree', headerName: 'Degree', sortable: false, editable: false, width: 250},
    {
      editable: false,
      sortable: false,
      width: 120,
      renderCell: (params) => (
          <button onClick={() => promote(params.row.id)} >
            Promote
          </button>
      ),
    },
  ];

  return(
        <Box sx={{ height: 400, width: '100%' }}>
          <DataGrid
              getRowId={(row) => row.id}
              sortModel={[{ field: 'fullName', sort: 'asc' }]}
              columns={columns}
              rows={lectors}
              disableColumnMenu
              disableRowSelectionOnClick
              pageSizeOptions={[5, 10, 15]}
              initialState={{
                pagination: {
                  paginationModel: { page: 0, pageSize: 5 },
                },
              }}
          />
        </Box>
  );

  function promote(lectorId) {
    LectorApi.promote(lectorId).then(({ data }) => {
      fetchTableData();
      NotificationManager.success('Lector successfully promoted')
    }).catch(err => {
      NotificationManager.error(err.response.data);
    });
  }
}

export default LectorTable;
