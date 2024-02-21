package com.mfitrahrmd.dicoding_belajar_fundamental_aplikasi_android

import org.junit.Assert.assertEquals
import org.junit.Before

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class MainViewModelTest {
    private lateinit var _viewModel: MainViewModel
    private lateinit var _cuboidModel: CuboidModel
    private val dummyWidth = 12.0
    private val dummyLength = 7.0
    private val dummyHeight = 6.0
    private val dummyVolume = 504.0
    private val dummyCircumference = 100.0
    private val dummySurfaceArea = 396.0

    @Before
    fun before() {
        _cuboidModel = mock(CuboidModel::class.java)
        _viewModel = MainViewModel(_cuboidModel)
    }

    @Test
    fun getCircumference() {
        _cuboidModel = CuboidModel()
        _viewModel = MainViewModel(_cuboidModel)
        _viewModel.save(dummyWidth, dummyLength, dummyHeight)
        val circumference = _viewModel.getCircumference()
        assertEquals(dummyCircumference, circumference, 0.0001)
    }

    @Test
    fun getSurfaceArea() {
        _cuboidModel = CuboidModel()
        _viewModel = MainViewModel(_cuboidModel)
        _viewModel.save(dummyWidth, dummyLength, dummyHeight)
        val surfaceArea = _viewModel.getSurfaceArea()
        assertEquals(dummySurfaceArea, surfaceArea, 0.0001)
    }

    @Test
    fun getVolume() {
        _cuboidModel = CuboidModel()
        _viewModel = MainViewModel(_cuboidModel)
        _viewModel.save(dummyWidth, dummyLength, dummyHeight)
        val volume = _viewModel.getVolume()
        assertEquals(dummyVolume, volume, 0.0001)
    }

    @Test
    fun testMockVolume() {
        `when`(_viewModel.getVolume()).thenReturn(dummyVolume)
        val volume = _viewModel.getVolume()
        verify(_cuboidModel).getVolume()
        assertEquals(dummyVolume, volume, 0.0001)
    }

    @Test
    fun testMockCircumference() {
        `when`(_viewModel.getCircumference()).thenReturn(dummyCircumference)
        val circumference = _viewModel.getCircumference()
        verify(_cuboidModel).getCircumference()
        assertEquals(dummyCircumference, circumference, 0.0001)
    }

    @Test
    fun testMockSurfaceArea() {
        `when`(_viewModel.getSurfaceArea()).thenReturn(dummySurfaceArea)
        val surfaceArea = _viewModel.getSurfaceArea()
        verify(_cuboidModel).getSurfaceArea()
        assertEquals(dummySurfaceArea, surfaceArea, 0.0001)
    }
}