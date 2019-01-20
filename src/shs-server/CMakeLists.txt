# CMakeList.txt : Top-level CMake project file, do global configuration
# and include sub-projects here.
#
cmake_minimum_required (VERSION 3.6)

project ("shs-server")

# Include sub-projects.
add_subdirectory ("server")
#add_subdirectory ("shs-api")
