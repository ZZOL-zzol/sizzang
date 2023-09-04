import { configureStore, createSlice } from '@reduxjs/toolkit';

const exampleState = createSlice({
  name: 'exampleState',
  initialState :{ value: 0},
  reducers: {
  	plusTest(state, action) {
        state.value += action.payload
    } 
  }
})

export const {plusTest} = exampleState.actions

export default configureStore({
  reducer: {
  	exampleState: exampleState.reducer
  },
});