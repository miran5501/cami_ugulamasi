class ApiResponse<T> {
  final bool success;
  final int status;
  final String message;
  final T? data;

  ApiResponse({
    required this.success,
    required this.status,
    required this.message,
    this.data,
  });

  factory ApiResponse.fromJson(
    Map<String, dynamic> json,
    T Function(dynamic) fromJsonT,
  ) {
    return ApiResponse(
      success: json['success'],
      status: json['status'],
      message: json['message'],
      data: json['data'] != null ? fromJsonT(json['data']) : null,
    );
  }
}
