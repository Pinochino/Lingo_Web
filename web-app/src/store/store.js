import { configureStore } from "@reduxjs/toolkit";
import testListSlice from "../slice/testListSlice";
import attemptSlice from "../slice/attempts"

export const store = configureStore({
    reducer: {
        tests: testListSlice,
        attempts: attemptSlice,
    },
});
