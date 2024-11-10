import {createSlice, PayloadAction} from '@reduxjs/toolkit';

interface SuppliersState {
    suppliers: Supplier[]
}

interface Supplier {
    id: number;
    description: string;
}

const initialState: SuppliersState = {
    suppliers: []
}

const suppliersSlice = createSlice({
    name: 'suppliers',
    initialState,
    reducers: {
        load: function (state, action: PayloadAction<Supplier[]>) {
            return {...state, suppliers: action.payload}
        },
        clear: function (state, action) {
            return initialState
        }
    },
});

export const {load, clear} = suppliersSlice.actions;

export default suppliersSlice;