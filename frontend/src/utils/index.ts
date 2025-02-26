// @ts-ignore
import Swal from 'sweetalert2/dist/sweetalert2.js'

export class Log {
  static info(message: string) {
    Swal.fire({
      title: message,
      icon: 'info',
      draggable: true
    })
  }

  static success(message: string) {
    Swal.fire({
      title: message,
      icon: 'success',
      draggable: true
    })
  }

  static error(message: string) {
    Swal.fire({
      title: message,
      icon: 'error',
      draggable: true
    })
  }
}
