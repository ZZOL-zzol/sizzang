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

const currentTab = createSlice({
  name: 'currentTab',
  initialState : {value : 0},
  reducers: {
    setCurrentTab(state, action) {
      state.value = action.payload;
    }
  }
})

const basketCount = createSlice({
  name: 'basketCount',
  initialState : {value : 0},
  reducers: {
    setBasketCount(state, action) {
      state.value = action.payload;
    }
  }
})

export const {plusTest} = exampleState.actions
export const {setCurrentTab} = currentTab.actions
export const {setBasketCount} = basketCount.actions

export default configureStore({
  reducer: {
  	exampleState: exampleState.reducer,
    currentTab: currentTab.reducer,
    basketCount : basketCount.reducer
  },
});