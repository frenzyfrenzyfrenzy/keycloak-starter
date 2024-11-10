import {configureStore} from '@reduxjs/toolkit';
import activitiesSlice from './activitiesSlice';
import suppliersSlice from "./suppliersSlice";
import {TypedUseSelectorHook, useDispatch, useSelector} from "react-redux";

const store = configureStore({
    reducer: {
        activities: activitiesSlice.reducer,
        suppliers: suppliersSlice.reducer,
    },
});

type AppDispatch = typeof store.dispatch
type RootState = ReturnType<typeof store.getState>

export const useAppDispatch = () => useDispatch<AppDispatch>();
export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;

export default store;