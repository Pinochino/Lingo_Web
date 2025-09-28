import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { allTests } from "../data/MockData";
import { getListTests } from "../config/api";


const initialState = {
  category: "all",
  status: "all",
  search: "",
  sort: "",
  page: 1,
  pageSize: 10,
  tests: allTests.tests  // mock data
};

export const retrieveTests = createAsyncThunk(
  "tests/retrieve",
  async (filters) => {
    const res = await getListTests(filters);
    return res.data;
  }
);

const testListSlice = createSlice({
  name: "tests",
  initialState,
  reducers: {
    setSort: (state, action) => {
      state.sort = action.payload
    },
    setSearch: (state, action) => {
      state.search = action.payload
    },
    setPage: (state, action) => {
      state.page = action.payload;
    },
    setPageSize: (state, action) => {
      state.pageSize = action.payload;
    },
    setStatus: (state, action) => {
      state.status = action.payload
    },
    setCategory: (state, action) => {
      state.category = action.payload
    },
    setTests: (state, action) => {
      state.tests = action.payload
    }
  },
  extraReducers: (builder) => {
    builder
      .addCase(retrieveTests.fulfilled, (state, action) => {
        state.tests = action.payload.data;
        state.page = action.payload.page;
        state.pageSize = action.payload.pageSize;
      })
  }

});

export const { setSort, setSearch, setPage, setPageSize, setStatus, setCategory, setTests } = testListSlice.actions;

export default testListSlice.reducer;